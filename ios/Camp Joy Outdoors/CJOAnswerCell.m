//
//  CJOAnswerCell.m
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOAnswerCell.h"
#import "UIView+FrameSize.h"
#import "CJOGlossaryTerm.h"
#import "CJOModel.h"

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
    //self.answerText.text = self.choice.text;
    /*CGRect frame = self.answerText.frame;*/
    
    NSArray * glossaryTerms = [CJOModel terms];
    self.textHeightConstraint.constant = self.answerText.contentSize.height;
    
    NSMutableAttributedString * val = [[NSMutableAttributedString alloc] initWithString:@"blah"];
    [val beginEditing];
    [val addAttribute:NSLinkAttributeName value:@"http://www.google.com" range:NSMakeRange(2, 2)];
    [val endEditing];
    self.answerText.attributedText = val;
    
    /*frame.size.height = self.answerText.contentSize.height;
    self.answerText.frame = frame;*/
}

-(BOOL)textView:(UITextView *)textView shouldInteractWithURL:(NSURL *)URL inRange:(NSRange)characterRange {
    return YES;
}


@end
