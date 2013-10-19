//
//  CJOTreeInfoViewController.h
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CJOTree.h"
#import "iCarousel.h"
#import "MWPhotoBrowser.h"


@interface CJOTreeInfoViewController : UITableViewController <UITableViewDataSource, iCarouselDelegate>
    @property (nonatomic, strong) CJOTree * tree;
@end
