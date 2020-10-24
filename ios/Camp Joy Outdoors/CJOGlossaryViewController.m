//
//  CJOGlossaryViewController.m
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOGlossaryViewController.h"
#import "CJOGlossaryDefinitionViewController.h"

@interface CJOGlossaryViewController ()
@property (strong, nonatomic) NSArray *terms;
@property (strong, nonatomic) CJOGlossaryTerm *selectedTerm;
@end

@implementation CJOGlossaryViewController

-(void)viewDidLoad
{
    self.terms = [[CJOModel terms] copy];
    
}

#pragma mark - TableView Datasource
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [self.terms count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"definition" forIndexPath:indexPath];
    
    return cell;
}

-(void)tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath
{
    CJOGlossaryTerm *term = [self.terms objectAtIndex:indexPath.row];
    cell.textLabel.text = term.name;
    cell.detailTextLabel.text = term.description2;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    self.selectedTerm = [self.terms objectAtIndex:indexPath.row];
    [self performSegueWithIdentifier:@"definition" sender:self];
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"definition"]) {
        CJOGlossaryDefinitionViewController *dvc = (CJOGlossaryDefinitionViewController *)segue.destinationViewController;
        dvc.word = self.selectedTerm.name;
    }
}

@end
