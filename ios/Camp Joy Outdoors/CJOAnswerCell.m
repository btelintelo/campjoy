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

// This absolutely horrid bit of code determines what word is tapped on in the text view
// It may be possible to make this simpler (perhaps enumerateEnclosingRects... enumerates over rects
// enclosing individual words? not really sure).  This seems to work, but definitely warrants
// revisiting.
- (void)textViewTapped:(id)sender {
    
    // Get the tap point in the coordinate system of the text view
    CGPoint location = [self.textViewGestureRecognizer locationInView:self.answerText];
    
    // Iterate over the ranges of individual words in the string
    NSRange range = NSMakeRange(0, 0);
    __block BOOL wordFound = NO;
    while (range.location != NSNotFound) {
        NSRange textRange = [self rangeInString:self.answerText.text afterRange:range];
        range = [self findRangeOfFirstWordInText:self.answerText.text withinRange:textRange];
        if(range.location != NSNotFound) {
            
            // Convert the character range to a glyph range, for handling special characters.  Shouldn't make a difference for our pursposes,
            // but better to be on the safe side.
            NSRange glyphRange = [self.answerText.textContainer.layoutManager glyphRangeForCharacterRange:range actualCharacterRange:NULL];
            
            // The only way I found to get rects containing individual words was using this method.
            [self.answerText.textContainer.layoutManager enumerateEnclosingRectsForGlyphRange:glyphRange withinSelectedGlyphRange:(NSRange){NSNotFound, 0} inTextContainer:self.answerText.textContainer usingBlock:^(CGRect rect, BOOL *stop) {
                
                // Correct the rect for the text views insets
                rect = CGRectMake(rect.origin.x + self.answerText.textContainerInset.left, rect.origin.y + self.answerText.textContainerInset.top, rect.size.width, rect.size.height);
                NSLog(@"Checking a word");
                
                // Inflate the rect we compare against a bit to account for near taps, and spaces between
                // multiword glossary terms. Only used in the comparison.
                CGRect comparisonRect = CGRectMake(rect.origin.x - 2, rect.origin.y - 2, rect.size.width + 4, rect.size.height + 4);
                
                // If the rect contains the tap point, we've found our word
                if(CGRectContainsPoint(comparisonRect, location)) {
                    rect = [self convertRect:rect fromView:self.answerText];
                    [self.answerText.textStorage enumerateAttribute:NSLinkAttributeName inRange:range options:0 usingBlock:^(id value, NSRange range, BOOL *stop) {
                        NSString *termName = (NSString *)value;
                        
                        // Assume only one word will be found and it will either have the link attribute as a whole
                        // or won't have it at all
                        if(termName) {
                            wordFound = YES;
                            CJOGlossaryTerm * glossaryTerm = [CJOModel findTermByName:termName];
                            [self.delegate answerCell:self didSelectGlossaryTerm:glossaryTerm boundByRect:rect];
                        }
                    }];
                }
            }];
        }
    }
    if(!wordFound)
        [self dispatchTap];
}


//Finds the range of the starting word in a string by first finding the first nonwhitespace character,
//then finding the first whitespace character, then creating a range from the difference.
-(NSRange) findRangeOfFirstWordInText:(NSString *) text withinRange:(NSRange)range {
    
    NSCharacterSet * whitespace = [NSCharacterSet whitespaceCharacterSet];
    NSCharacterSet * nonWhitespace = [whitespace invertedSet];
    
    // Contains the full length of the input string
    NSRange fullRange = NSMakeRange(0, text.length);
    
    // Look for the start of a word
    NSRange start = [text rangeOfCharacterFromSet:nonWhitespace options:0 range:range];
    if(start.location != NSNotFound) {
        
        // Look for the end of the string in the remainder of the string
        range = [self rangeInString:text afterRange:start];
        NSRange end = [text rangeOfCharacterFromSet:whitespace options:0 range:range];
        if(end.location != NSNotFound) {
            return NSMakeRange(start.location, end.location - start.location);
        }
        
        // Return a range containing the remainder of the string if we don't find more whitespace
        return NSMakeRange(start.location, fullRange.length - start.location);
    }
    // If we couldn't find the start of a word, return not found
    return NSMakeRange(NSNotFound, 0);
}

// Given a starting range, returns a range representing the remainder of the string
-(NSRange) rangeInString:(NSString *) string afterRange:(NSRange)start {
    NSRange fullRange = NSMakeRange(0, string.length);
    return NSMakeRange(start.location + start.length, fullRange.length - (start.location + start.length));
}

- (IBAction)accessoryTapped:(id)sender {
    [self dispatchTap];
}

-(void) dispatchTap {
    
    NSIndexPath *indexPath = [self.tableView indexPathForCell:self];
    [self.tableView selectRowAtIndexPath:indexPath animated:YES scrollPosition:UITableViewScrollPositionNone];
    [self.tableView.delegate tableView:self.tableView didSelectRowAtIndexPath:indexPath];
    
}


-(void)layoutSubviews {
    self.textViewGestureRecognizer = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(textViewTapped:)];
    self.answerText.gestureRecognizers = @[self.textViewGestureRecognizer];
    NSArray * glossaryTerms = [CJOModel termStringsWithPlurals];
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
            
#warning This is kind of a hack, but it should work... I look to make I don't have the same string w/o the s to deal with plural characters... if I do, I use the original string
            NSString *termForURL = term;
            NSString *termForURLWithOutLastCharacter = [termForURL substringToIndex:([termForURL length]-1)];
            if([terms containsObject:termForURLWithOutLastCharacter]) {
                termForURL = termForURLWithOutLastCharacter;
            }
            [matchedString addAttribute:NSLinkAttributeName value:termForURL range:range];
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


// This matching could be much better. Currently, when we have a partial match, just extend the match to include
// whole words instead of leaving out parts of words.
/* Kept for reference
-(NSArray *)rangesOfString:(NSString *)string inString:(NSString *)source {
    NSMutableArray * ranges = [[NSMutableArray alloc] init];
    NSUInteger count = 0, length = [source length];
    NSRange range = NSMakeRange(0, length);
        
    while(range.location != NSNotFound)
    {
        range = [source rangeOfString:string options:0 range:range];
        if(range.location != NSNotFound) {
            if(range.location > 0) {
                NSRange head = NSMakeRange(0, range.location);
                head = [source rangeOfCharacterFromSet:[NSCharacterSet whitespaceCharacterSet] options:NSBackwardsSearch range:head];
                if(head.location == NSNotFound) {
                    range.location = 0;
                } else {
                    range.location = head.location + 1;
                }
            }
            if(range.location + range.length < source.length) {
                NSRange tail = NSMakeRange(range.location + range.length, source.length - (range.location + range.length));
                tail = [source rangeOfCharacterFromSet:[NSCharacterSet whitespaceCharacterSet] options:0 range:tail];
                if(tail.location  == NSNotFound) {
                    range.length = source.length - range.location;
                } else {
                    range.length = tail.location - range.location;
                }
            }
            [source characterAtIndex:range.location];
            [ranges addObject:[NSValue valueWithRange:range]];
            range = NSMakeRange(range.location + range.length, length - (range.location + range.length));
            count++;
        }
    }
    return ranges;
}
*/
@end
