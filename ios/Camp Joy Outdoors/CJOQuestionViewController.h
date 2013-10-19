//
//  CJOQuestionViewController.h
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CJOQuestion.h"

@interface CJOQuestionViewController : UICollectionViewController<UICollectionViewDelegateFlowLayout, UICollectionViewDataSource>

@property (nonatomic, strong) CJOQuestion * question;
@property (weak, nonatomic) IBOutlet UILabel *firstAnswerLabel;
@property (weak, nonatomic) IBOutlet UIImageView *firstAnswerImage;
@property (weak, nonatomic) IBOutlet UILabel *secondAnswerLabel;
@property (weak, nonatomic) IBOutlet UIImageView *secondAnswerImage;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *firstImageHeightConstraint;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *firstAnswerHeightConstraint;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *secondImageHeightConstraint;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *secondAnswerHeightConstraint;
@end
