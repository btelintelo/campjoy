//
//  CJOAppDelegate.m
//  Camp Joy Outdoors
//
//  Created by Brian Telintelo on 10/16/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOAppDelegate.h"
#import "CJOModel.h"
#import "CJOTreeImagesDataSource.h"
#import "CJOConstants.h"

@implementation CJOAppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    NSDictionary *boardMap = @{@"Identify":@"Identify", @"glossary":@"Glossary"};

    
    NSString *fmt = UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPhone ? @"%@_iPhone" : @"%@_iPad";
    NSMutableArray *controllers = [[NSMutableArray alloc]initWithCapacity:[boardMap count]];
    for (NSString *name in [boardMap allKeys]) {
        UIStoryboard *board = [UIStoryboard storyboardWithName:[NSString stringWithFormat:fmt, name] bundle:nil];
        UIViewController *controller = [board instantiateInitialViewController];
        controller.title = boardMap[name];
        [controllers addObject:controller];
        
    }
    
    UITabBarController *tabc = (UITabBarController *)self.window.rootViewController;
    [controllers insertObject:tabc.viewControllers[0] atIndex:0];
    [controllers addObject:[tabc.viewControllers lastObject]];
    [tabc setViewControllers:controllers];
    
    return YES;
}
							
- (void)applicationWillResignActive:(UIApplication *)application
{
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later. 
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication *)application
{
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}

- (BOOL)application:(UIApplication *)application handleOpenURL:(NSURL *)url {
    // Assuming this is a url for glossary, since that's all we have right now
    NSMutableDictionary *params = [[NSMutableDictionary alloc] init];
    NSString * query = url.query;
    for (NSString *param in [query componentsSeparatedByString:@"&"]) {
        NSArray *elts = [param componentsSeparatedByString:@"="];
        if([elts count] < 2) continue;
        [params setObject:[elts objectAtIndex:1] forKey:[elts objectAtIndex:0]];
    }
    [[NSNotificationCenter defaultCenter] postNotificationName:SHOW_GLOSSARY_NOTIFICATION object:nil userInfo:params];
    return YES;
}

@end
