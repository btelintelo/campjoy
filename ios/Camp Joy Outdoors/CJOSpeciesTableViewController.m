//
//  CJOSpeciesTableViewController.m
//  Camp Joy Outdoors
//
//  Created by Brian Telintelo on 10/19/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOSpeciesTableViewController.h"
#import "CJOTree.h"
#import "CJOTreeInfoViewController.h"

@interface CJOSpeciesTableViewController ()
@property (weak, nonatomic) IBOutlet UITableView *tableView;

@end

@implementation CJOSpeciesTableViewController

-(void)viewDidLoad
{
    
}

#pragma mark - TableView Datasource
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [[CJOModel trees] count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"tree" forIndexPath:indexPath];
    
    return cell;
}

-(void)tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath
{
    CJOTree *tree = [[CJOModel trees] objectAtIndex:indexPath.row];
    cell.textLabel.text = tree.name;
    NSString * imageName = [NSString stringWithFormat:@"thumbs/%@_thumb.jpg", tree.id];
    UIImage *img = [UIImage imageNamed:imageName];
    cell.imageView.image = img;
}

CJOTree *tree;

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    tree = [[CJOModel trees] objectAtIndex:indexPath.row];
    CJOTreeInfoViewController *treeInfo = [[CJOTreeInfoViewController alloc] init];
    treeInfo.tree = tree;
    [self.navigationController pushViewController:treeInfo animated:YES];
}


@end
