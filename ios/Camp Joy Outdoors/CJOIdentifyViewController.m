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
@property(nonatomic, strong) UIViewController * navigationController;

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
    self.navigationController = [storyboard instantiateViewControllerWithIdentifier:@"IdentifyNavigationController"];
    self.navigationController.view.frame = self.view.bounds;
    [self.view addSubview:self.navigationController.view];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
