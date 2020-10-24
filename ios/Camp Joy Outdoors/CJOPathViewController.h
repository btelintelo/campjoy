//
//  CJOPathViewController.h
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/19/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol CJOPathViewControllerDelegate;

@interface CJOPathViewController : UITableViewController
@property(nonatomic, weak) id<CJOPathViewControllerDelegate> delegate;
@property(nonatomic, strong) NSArray * path;
- (IBAction)donePressed:(id)sender;
@end

@protocol CJOPathViewControllerDelegate
-(void)pathViewControllerDone;
-(void)pathViewControllerDidSelectIndex:(NSInteger)index;
@end
