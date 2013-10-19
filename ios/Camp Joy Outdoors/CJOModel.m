//
//  CJOModel.m
//  Camp Joy Outdoors
//
//  Created by Brian Telintelo on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOModel.h"
#import "CJOJSONParser.h"

@implementation CJOModel

+(NSArray *) questions
{
    return [CJOJSONParser questions];
}
+(NSArray *) trees
{
    return [CJOJSONParser trees];
}
+(NSArray *) terms
{
    return [CJOJSONParser terms];
}
@end
