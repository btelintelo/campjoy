//
//  CJOModel.h
//  Camp Joy Outdoors
//
//  Created by Brian Telintelo on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "CJOQuestion.h"
#import "CJOGlossaryTerm.h"
#import "CJOTree.h"

@interface CJOModel : NSObject

+(NSArray *) questions;
+(NSArray *) trees;
+(NSArray *) terms;

+(NSArray *) termStrings;
+(CJOQuestion *) findQuestionById:(int) questionId;

@end
