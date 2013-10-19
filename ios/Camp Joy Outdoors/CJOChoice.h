//
//  CJOChoice.h
//  Camp Joy Outdoors
//
//  Created by Brian Telintelo on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CJOChoice : NSObject

@property (nonatomic, retain) NSString *choiceId;
@property (nonatomic, retain) NSString *text;
@property (nonatomic, retain) NSNumber *nextId;
@property (nonatomic, retain) NSNumber *treeId;

@end