//
//  CJOAnswerCell.h
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CJOChoice.h"

@protocol CJOAnswerCellDelegate;

@interface CJOAnswerCell : UITableViewCell<UITextViewDelegate, UIGestureRecognizerDelegate>

@property (nonatomic, weak) id<CJOAnswerCellDelegate> delegate;
@property (weak, nonatomic) IBOutlet UIImageView *image;
@property (weak, nonatomic) IBOutlet UITextView *answerText;
@property (strong, nonatomic) UIImage * choiceImage;
@property (strong, nonatomic) CJOChoice * choice;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *textHeightConstraint;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *imageWidthConstraint;
@property (weak, nonatomic) UITableView* tableView;

@end

@protocol CJOAnswerCellDelegate <NSObject>

-(void)answerCell:(CJOAnswerCell *)cell didSelectGlossaryTerm:(CJOGlossaryTerm *)glossaryTerm boundByRect:(CGRect)rect;

@end