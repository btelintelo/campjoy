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
    NSArray * glossaryTerms = [CJOModel termStrings];
    self.textHeightConstraint.constant = self.answerText.contentSize.height;
    
    self.answerText.attributedText = [self matchTerms:glossaryTerms inString:self.choice.text];
}

-(NSAttributedString *) matchTerms:(NSArray *)terms inString:(NSString *)string {
    NSMutableAttributedString * matchedString = [[NSMutableAttributedString alloc] initWithString:string];
    [matchedString beginEditing];
    for(NSString *term in terms) {
        NSArray * ranges = [self rangesOfString:term inString:string];
        for(NSValue *value in ranges) {
            NSRange range = [value rangeValue];
            [matchedString addAttribute:NSLinkAttributeName value:[NSString stringWithFormat:@"identitree://glossary?term=%@", [term stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]] range:range];
        }
    }
    [matchedString endEditing];
    return matchedString;
}

-(BOOL)textView:(UITextView *)textView shouldInteractWithURL:(NSURL *)URL inRange:(NSRange)characterRange {
    return YES;
}

-(NSArray *)rangesOfString:(NSString *)string inString:(NSString *)source {
    NSMutableArray * ranges = [[NSMutableArray alloc] init];
    NSUInteger count = 0, length = [source length];
    NSRange range = NSMakeRange(0, length);
        
    while(range.location != NSNotFound)
    {
        range = [source rangeOfString:string options:0 range:range];
        if(range.location != NSNotFound) {
            [ranges addObject:[NSValue valueWithRange:range]];
            range = NSMakeRange(range.location + range.length, length - (range.location + range.length));
            count++;
        }
    }
    return ranges;
}


@end
