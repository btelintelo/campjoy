//
//  CJOInfoViewController.m
//  Camp Joy Outdoors
//
//  Created by Brian Telintelo on 10/20/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOInfoViewController.h"

@interface CJOInfoViewController ()
- (IBAction)donePressed:(id)sender;

@end

@implementation CJOInfoViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
}
- (IBAction)closePushed:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)donePressed:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}


@end


@implementation CJOInfoViewTableController

- (IBAction)closePushed:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}

@end


