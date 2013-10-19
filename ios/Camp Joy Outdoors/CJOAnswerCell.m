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

@interface CJOAnswerCell()
@property (nonatomic, strong) UITapGestureRecognizer * textViewGestureRecognizer;

@end

@implementation CJOAnswerCell

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
    }
    return self;
}

//Taking this out for now, just going to enable tap on disclosure indicator
/*
- (void)textViewTapped:(id)sender {
    CGPoint location = [self.textViewGestureRecognizer locationInView:self.answerText];
    NSUInteger index = [self.answerText.textContainer.layoutManager glyphIndexForPoint:location inTextContainer:self.answerText.textContainer];
    //CGGlyph glyph = [self.answerText.textContainer.layoutManager glyphAtIndex:index];
    NSUInteger characterIndex = [self.answerText.textContainer.layoutManager characterIndexForGlyphAtIndex:index];
    NSLog(@"%d", characterIndex);
    NSLog(@"%@", self.answerText.textStorage);
    NSLog(@"%c",[self.answerText.text characterAtIndex:characterIndex]);
    
    [self dispatchTap];
}
*/
- (IBAction)accessoryTapped:(id)sender {
    [self dispatchTap];
}

-(void) dispatchTap {
    
    NSIndexPath *indexPath = [self.tableView indexPathForCell:self];
    [self.tableView selectRowAtIndexPath:indexPath animated:YES scrollPosition:UITableViewScrollPositionNone];
    [self.tableView.delegate tableView:self.tableView didSelectRowAtIndexPath:indexPath];
    
}


-(void)layoutSubviews {
    /*self.textViewGestureRecognizer = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(textViewTapped:)];
    self.answerText.gestureRecognizers = @[self.textViewGestureRecognizer];*/
    NSArray * glossaryTerms = [CJOModel termStrings];
    self.answerText.attributedText = [self matchTerms:glossaryTerms inString:self.choice.text];
    self.image.image = self.choiceImage;
    CGFloat aspectRatio = self.choiceImage.size.width / self.choiceImage.size.height;
    if(aspectRatio != INFINITY && aspectRatio != 0 && !isnan(aspectRatio)) {
        CGFloat imageHeight = 75;
        CGFloat imageWidth = imageHeight * aspectRatio;
        if(imageWidth > 267) {
            imageWidth = 267;
            imageHeight = imageWidth / aspectRatio;
        }
            
        self.imageWidthConstraint.constant = imageWidth;
    }
    self.answerText.delegate = self;
    dispatch_async(dispatch_get_main_queue(), ^{
        self.textHeightConstraint.constant = self.answerText.contentSize.height;
        self.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
    });
    
}


-(NSAttributedString *) matchTerms:(NSArray *)terms inString:(NSString *)string {
    NSMutableAttributedString * matchedString = [[NSMutableAttributedString alloc] initWithString:string];
    string = [string lowercaseString];
    [matchedString beginEditing];
    for(NSString *term in terms) {
        NSString *lowerTerm = [term lowercaseString];
        NSArray * ranges = [self rangesOfString:lowerTerm inString:string];
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
