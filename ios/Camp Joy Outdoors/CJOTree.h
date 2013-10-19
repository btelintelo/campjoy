//
//  CJOTree.h
//  Camp Joy Outdoors
//
//  Created by Brian Telintelo on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CJOTree : NSObject

@property (nonatomic, retain) NSNumber *treeId;
@property (nonatomic, retain) NSString *name;
@property (nonatomic, retain) NSString *description;
@property (nonatomic, retain) NSString *sciname;
@property (nonatomic, retain) NSString *family;
@property (nonatomic, retain) NSDictionary *tableData;

@end
