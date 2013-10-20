//
//  CJOPathCell.h
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/19/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CJOChoice.h"

@interface CJOPathCell : UITableViewCell
@property (strong, nonatomic) IBOutlet UILabel *stepLabel;
@property (nonatomic, strong) CJOChoice * choice;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *labelHeightConstraint;
@end
