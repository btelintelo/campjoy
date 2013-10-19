//
//  CJOAnswerCell.m
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOAnswerCell.h"

@implementation CJOAnswerCell

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
    }
    return self;
}

-(void)layoutSubviews {
    self.answerText.text = self.choice.text;
}

@end
