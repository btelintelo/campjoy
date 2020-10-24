//
//  CJOFirstViewController.m
//  Camp Joy Outdoors
//
//  Created by Brian Telintelo on 10/16/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import <CoreGraphics/CoreGraphics.h>

#import "CJOHomeViewController.h"

#import "CJOInfoViewController.h"

@interface CJOHomeViewController ()
@property (weak, nonatomic) IBOutlet UIButton *startButton;

@end

@implementation CJOHomeViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
//	[self.startButton setBackgroundColor:[self colorWithHexString:@"#808285"]];
    self.startButton.layer.cornerRadius = 5;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (IBAction)startButtonPressed:(id)sender {
    self.tabBarController.selectedViewController
    = [self.tabBarController.viewControllers objectAtIndex:1];
}


-(UIColor*)colorWithHexString:(NSString*)hex
{
    NSString *cString = [[hex stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]] uppercaseString];
    
    // String should be 6 or 8 characters
    if ([cString length] < 6) return [UIColor grayColor];
    
    // strip 0X if it appears
    if ([cString hasPrefix:@"0X"]) cString = [cString substringFromIndex:2];
    
    if ([cString length] != 6) return  [UIColor grayColor];
    
    // Separate into r, g, b substrings
    NSRange range;
    range.location = 0;
    range.length = 2;
    NSString *rString = [cString substringWithRange:range];
    
    range.location = 2;
    NSString *gString = [cString substringWithRange:range];
    
    range.location = 4;
    NSString *bString = [cString substringWithRange:range];
    
    // Scan values
    unsigned int r, g, b;
    [[NSScanner scannerWithString:rString] scanHexInt:&r];
    [[NSScanner scannerWithString:gString] scanHexInt:&g];
    [[NSScanner scannerWithString:bString] scanHexInt:&b];
    
    return [UIColor colorWithRed:((float) r / 255.0f)
                           green:((float) g / 255.0f)
                            blue:((float) b / 255.0f)
                           alpha:1.0f];
}


@end
