//
//  CJOAnswerCell.h
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CJOChoice.h"

@interface CJOAnswerCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UIImageView *image;
@property (weak, nonatomic) IBOutlet UILabel *answerText;
@property (strong, nonatomic) CJOChoice * choice;

@end
