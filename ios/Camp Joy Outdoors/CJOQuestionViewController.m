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

-(NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView {
    return 1;
}
-(NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section {
    return self.question.choices.count;
}

-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath {
    UICollectionViewCell * cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"AnswerImageCell" forIndexPath:indexPath];
    ((CJOAnswerCell *) cell).choice = self.question.choices[indexPath.row];
    return cell;
}

-(void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath {
    CJOAnswerCell * cell = (CJOAnswerCell *)[collectionView dequeueReusableCellWithReuseIdentifier:@"AnswerImageCell" forIndexPath:indexPath];
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

//We'll reduce the height if there's no image to display
/*
-(CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath {
    
    return CGSizeMake(320, 158);
}

-(NSString *) imageForIndexPath:(NSIndexPath *) indexPath {
    return nil;
}
*/

@end
