//
//  CJOInfoViewController.h
//  Camp Joy Outdoors
//
//  Created by Brian Telintelo on 10/20/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol  InfoDelegate;


@interface CJOInfoViewController : UIViewController

@property (weak) id <InfoDelegate> delegate;

@end


@protocol InfoDelegate

-(void)closeModal;

@end