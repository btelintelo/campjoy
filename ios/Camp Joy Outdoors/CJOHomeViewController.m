//
//  CJOFirstViewController.m
//  Camp Joy Outdoors
//
//  Created by Brian Telintelo on 10/16/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOHomeViewController.h"

@interface CJOHomeViewController ()

@end

@implementation CJOHomeViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
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

@end
