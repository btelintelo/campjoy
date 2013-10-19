//
//  CJOSecondViewController.m
//  Camp Joy Outdoors
//
//  Created by Brian Telintelo on 10/16/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOIdentifyViewController.h"
#import "CJOUtilities.h"

@interface CJOIdentifyViewController ()

@end

@implementation CJOIdentifyViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	
    NSString * storyboardName = @"Identify_iPhone";
    if([CJOUtilities isIPad]) {
        storyboardName = @"Identify_iPad";
    }
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:storyboardName bundle:nil];
    UIViewController *navigationController = [storyboard instantiateViewControllerWithIdentifier:@"IdentifyNavigationController"];
    [self.view addSubview:navigationController.view];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
