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

@interface CJOQuestionViewController ()

@end

@implementation CJOQuestionViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        
    }
    return self;
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
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 0;
}

-(void)tableView:(UITableView *)tableView didSelectItemAtIndexPath:(NSIndexPath *)indexPath {
    CJOAnswerCell * cell = (CJOAnswerCell *)[tableView cellForRowAtIndexPath:indexPath];
    CJOChoice * choice = cell.choice;

    if(choice.nextid) {
        [self performSegueWithIdentifier:@"nextQuestionSegue" sender:cell];
    } else if(choice.treeid) {
        [self performSegueWithIdentifier:@"treeIdentifiedSegue" sender:cell];
    }
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    CJOAnswerCell * cell = (CJOAnswerCell *) sender;
    if([segue.identifier isEqualToString:@"nextQuestionSegue"]) {
        CJOQuestionViewController * destinationViewController = [segue destinationViewController];
        destinationViewController.question = [self nextQuestion:cell.choice];
    } else if ([segue.identifier isEqualToString:@"treeIdentifiedSegue"]) {
        CJOTreeInfoViewController * destinationViewController = [segue destinationViewController];
        destinationViewController.tree = [self identifiedTree:cell.choice];
    }
}

-(CJOQuestion *) nextQuestion: (CJOChoice *) choice {
    return self.question;
}

-(CJOTree *) identifiedTree: (CJOChoice *) choice {
    return nil;
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    CJOChoice * choice = self.question.choices[indexPath.row];
    UIImage * choiceImage = [self imageForIndexPath:indexPath];
    int height = 0;
    if(choiceImage) {
        height += 75;
    }
    
    CGRect textSize = [self measureText:choice.text withFont:[UIFont systemFontOfSize:17] andWidth:280];
    height += textSize.size.height;
    
    // Include room for spacing
    height += 48;
    
    return height;
}

-(CGRect) measureText:(NSString *) text withFont:(UIFont *) font andWidth:(CGFloat)width{
    NSDictionary * attributes = @{ NSFontAttributeName: font };
    NSAttributedString *attributedText = [[NSAttributedString alloc] initWithString:text attributes:attributes];
    return [attributedText boundingRectWithSize:(CGSize){width, CGFLOAT_MAX} options:NSStringDrawingUsesLineFragmentOrigin context:nil];
}

-(UIImage *) imageForIndexPath:(NSIndexPath *) indexPath {
    NSString * imageIndex = @"";
    if(indexPath.row == 0) {
        imageIndex = @"a";
    }
    else if (indexPath.row == 1) {
        imageIndex = @"b";
    }
    NSString * imageName = [NSString stringWithFormat:@"Assets/%@%@", self.question.id, imageIndex];
    return [UIImage imageNamed:imageName];
}


@end
