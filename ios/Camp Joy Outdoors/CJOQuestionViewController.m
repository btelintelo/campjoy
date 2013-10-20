//
//  CJOQuestionViewController.m
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOQuestionViewController.h"
#import "CJOAnswerCell.h"
#import "CJOTree.h"
#import "CJOTreeInfoViewController.h"
#import "CJOModel.h"
#import "CJOConstants.h"
#import "CJOGlossaryDefinitionViewController.h"
#import "CJOPathViewController.h"
#import "CJOUtilities.h"

@interface CJOQuestionViewController ()
@property (nonatomic, strong) UIPopoverController * popover;
@end

@implementation CJOQuestionViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        
    }
    return self;
}

-(void)viewDidLoad {
    self.tableView.contentInset = UIEdgeInsetsMake(-36, 0, 0, 0);
    if(self.hideRestartButton) {
        self.navigationItem.rightBarButtonItem = nil;
    }
}


-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return self.question.choices.count;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    UITableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"AnswerImageCell"];
    ((CJOAnswerCell *) cell).choice = self.question.choices[indexPath.row];
    ((CJOAnswerCell *) cell).choiceImage = [self imageForIndexPath:indexPath];
    ((CJOAnswerCell *) cell).tableView = tableView;
    ((CJOAnswerCell *) cell).delegate = self;
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 0;
}


-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {

    CJOAnswerCell * cell = (CJOAnswerCell *)[tableView cellForRowAtIndexPath:indexPath];
    CJOChoice * choice = cell.choice;

    if(choice.nextid.length > 0) {
        [self performSegueWithIdentifier:@"nextQuestionSegue" sender:cell];
    } else if(choice.treeid.length > 0) {
        [self performSegueWithIdentifier:@"treeIdentifiedSegue" sender:cell];
    }
}


-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    CJOAnswerCell * cell = (CJOAnswerCell *) sender;
    if([segue.identifier isEqualToString:@"nextQuestionSegue"]) {
        CJOQuestionViewController * destinationViewController = [segue destinationViewController];
        CJOQuestion * question = [self nextQuestion:cell.choice];
        destinationViewController.path = [self.path arrayByAddingObject:cell.choice];
        destinationViewController.question = question;
    } else if ([segue.identifier isEqualToString:@"treeIdentifiedSegue"]) {
        CJOTreeInfoViewController * destinationViewController = [segue destinationViewController];
        destinationViewController.tree = [self identifiedTree:cell.choice];
    } else if ([segue.identifier isEqualToString:@"showHistorySegue"]) {
        UINavigationController * navigationViewController = [segue destinationViewController];
        CJOPathViewController * destinationViewController = navigationViewController.viewControllers[0];
        destinationViewController.delegate = self;
        destinationViewController.path = self.path;
    }
}

-(CJOQuestion *) nextQuestion: (CJOChoice *) choice {
    return [CJOModel findQuestionById:choice.nextid];
}

-(CJOTree *) identifiedTree: (CJOChoice *) choice {
    return [CJOModel findTreeById:choice.treeid];
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    CJOChoice * choice = self.question.choices[indexPath.row];
    UIImage * choiceImage = [self imageForIndexPath:indexPath];
    
    // Include room for spacing
    int height = 48;
    if(choiceImage) {
        height += 75;
    }
    
    CGRect textSize = [self measureText:choice.text withFont:[UIFont systemFontOfSize:17] andWidth:280];
    height += textSize.size.height;
    
    return height;
}

-(CGRect) measureText:(NSString *) text withFont:(UIFont *) font andWidth:(CGFloat)width{
    NSDictionary * attributes = @{ NSFontAttributeName: font };
    NSAttributedString *attributedText = [[NSAttributedString alloc] initWithString:text attributes:attributes];
    return [attributedText boundingRectWithSize:(CGSize){width, CGFLOAT_MAX} options:NSStringDrawingUsesLineFragmentOrigin context:nil];
}

- (IBAction)restartQuestions:(id)sender {
    [self.navigationController popToRootViewControllerAnimated:YES];
}

-(UIImage *) imageForIndexPath:(NSIndexPath *) indexPath {
    NSString * imageIndex = @"";
    if(indexPath.row == 0) {
        imageIndex = @"a";
    }
    else if (indexPath.row == 1) {
        imageIndex = @"b";
    }
    NSString * imageName = [NSString stringWithFormat:@"dichotomy/%@%@", self.question.id, imageIndex];
    return [UIImage imageNamed:imageName];
}

-(void)pathViewControllerDone {
    [self dismissViewControllerAnimated:YES completion:nil];
}
-(void)pathViewControllerDidSelectIndex:(NSInteger)index {
    [self dismissViewControllerAnimated:YES completion:nil];
    [self.navigationController popToViewController:self.navigationController.viewControllers[index] animated:YES];
}

-(void)answerCell:(CJOAnswerCell *)cell didSelectGlossaryTerm:(CJOGlossaryTerm *)glossaryTerm boundByRect:(CGRect)rect {
    if([CJOUtilities isIPad]) {
        [self presentGlossaryTerm:glossaryTerm popoverFromRect:rect inView:cell];
    }
    else {
        [self launchApplicationURLForGlossaryTerm:glossaryTerm];
    }
}

-(void)launchApplicationURLForGlossaryTerm:(CJOGlossaryTerm *) glossaryTerm {
    NSURL * url = [NSURL URLWithString:[NSString stringWithFormat:@"identitree://glossary?term=%@", glossaryTerm.name]];
    [[UIApplication sharedApplication] openURL:url];
}

-(void)presentGlossaryTerm:(CJOGlossaryTerm *)glossaryTerm popoverFromRect:(CGRect)rect inView:(UIView *)view {
    UIStoryboard * storyboard = [UIStoryboard storyboardWithName:@"glossary_iPhone" bundle:nil];
    CJOGlossaryDefinitionViewController * viewController = [storyboard instantiateViewControllerWithIdentifier:@"definition"];
    viewController.word = glossaryTerm.name;
    viewController.edgesForExtendedLayout = UIRectEdgeNone;
    viewController.extendedLayoutIncludesOpaqueBars = YES;
    UINavigationController * rootController = [[UINavigationController alloc] initWithRootViewController:viewController];
    self.popover = [[UIPopoverController alloc] initWithContentViewController:rootController];
    [self.popover presentPopoverFromRect:rect inView:view permittedArrowDirections:UIPopoverArrowDirectionAny animated:YES];
}


@end
