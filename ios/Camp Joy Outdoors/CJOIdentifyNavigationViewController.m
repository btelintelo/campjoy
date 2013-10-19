//
//  CJOIdentifyNavigationViewController.m
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOIdentifyNavigationViewController.h"
#import "CJOQuestion.h"
#import "CJOQuestionViewController.h"
#import "CJOModel.h"

@interface CJOIdentifyNavigationViewController ()

@end

@implementation CJOIdentifyNavigationViewController

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
	
    // Get initial question
    CJOQuestion * initialQuestion = (CJOQuestion *)[CJOModel questions][0];
    
    // set question on root view controller
    CJOQuestionViewController * rootViewController = (CJOQuestionViewController *)self.viewControllers[0];
    rootViewController.question = initialQuestion;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
