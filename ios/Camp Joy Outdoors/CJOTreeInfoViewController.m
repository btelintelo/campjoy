//
//  CJOTreeInfoViewController.m
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOTreeInfoViewController.h"
#import "CJOModel.h"

@interface CJOTreeInfoViewController ()
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *descriptionHeightConstraint;
@property (weak, nonatomic) IBOutlet UILabel *scientificNameLabel;
@property (weak, nonatomic) IBOutlet UITextView *descriptionTextView;
@property (weak, nonatomic) IBOutlet UIView *carousel;
@end

@implementation CJOTreeInfoViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)resizeHeaderViewsForDescriptionText
{
//    [self.descriptionTextView sizeToFit];
    CGSize descriptionSize = [self.tree.description sizeWithAttributes:@{NSFontAttributeName: self.descriptionTextView.font}];
                              
//                              [self.tree.description sizeWithFont:self.descriptionTextView.font forWidth:self.descriptionTextView.bounds.size.width lineBreakMode:NSLineBreakByWordWrapping];
    self.descriptionHeightConstraint.constant = descriptionSize.height + 20;
    self.tableView.tableHeaderView.bounds = CGRectMake(0, 0, self.tableView.bounds.size.width, descriptionSize.height + self.carousel.bounds.size.height + 10);
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    self.tree = [CJOModel trees][0];
    self.navigationItem.title = self.tree.name;
    self.scientificNameLabel.text = [NSString stringWithFormat:@"%@ - %@",self.tree.sciname,self.tree.family];
    self.descriptionTextView.text = self.tree.description;

    [self resizeHeaderViewsForDescriptionText];

	// Do any additional setup after loading the view.
}

#pragma mark - UITableViewDataSource;
- (int) numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section {
    return @"Information";
}

- (int) tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return self.tree.tableData.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"TreeDataCell"];
    
    NSDictionary *data = self.tree.tableData[indexPath.row];
    NSString *name = data.allKeys[0];
    NSString *value = data[name];
    
    cell.textLabel.text = name;
    cell.detailTextLabel.text = value;
    return cell;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
