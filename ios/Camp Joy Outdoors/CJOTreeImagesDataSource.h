//
//  CJOTreeImagesDataSource.h
//  Camp Joy Outdoors
//
//  Created by Alex Argo on 10/19/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "CJOTree.h"
#import "iCarousel.h"

@interface CJOTreeImagesDataSource : NSObject

- (id) initWithTree:(CJOTree *)tree;

@property (nonatomic, strong) NSArray *imagePaths;

@end
