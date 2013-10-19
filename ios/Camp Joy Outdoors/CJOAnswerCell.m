//
//  CJOAnswerCell.m
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOAnswerCell.h"
#import "UIView+FrameSize.h"

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
//    self.answerText set
    if(self.choiceImage == nil) {
        self.image.hidden = YES;
    }
}

-(CGRect) measureText:(NSString *) text withFont:(UIFont *) font andWidth:(CGFloat)width{
    NSDictionary * attributes = @{ NSFontAttributeName: font };
    NSAttributedString *attributedText = [[NSAttributedString alloc] initWithString:text attributes:attributes];
    return [attributedText boundingRectWithSize:(CGSize){width, CGFLOAT_MAX} options:NSStringDrawingUsesLineFragmentOrigin context:nil];
}

@end
