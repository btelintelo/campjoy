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
#import "CJOConstants.h"
#import "CJOGlossaryDefinitionViewController.h"

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
    rootViewController.path = @[];
    rootViewController.hideHistoryButton = YES;
    rootViewController.hideRestartButton = YES;
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(handleGlossaryNotification:) name:SHOW_GLOSSARY_NOTIFICATION object:nil];
}

-(void)handleGlossaryNotification:(NSNotification *) notification {
    NSDictionary * userInfo = notification.userInfo;
    NSString * term = userInfo[@"term"];

    NSString *boardName = UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPhone ? @"glossary_iPhone" : @"glossary_iPad";
    UIStoryboard *board = [UIStoryboard storyboardWithName:boardName  bundle:nil];
    
    CJOGlossaryDefinitionViewController *definitionViewController = [board instantiateViewControllerWithIdentifier:@"definition"];
    definitionViewController.word = term;
    [self pushViewController:definitionViewController animated:YES];

}


@end
