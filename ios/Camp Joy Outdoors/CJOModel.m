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

NSArray *questions;
NSArray *trees;
NSArray *terms;

+(NSArray *) questions
{
    if(!questions)
        questions = [CJOJSONParser questions];
    return questions;
}
+(NSArray *) trees
{
    if(!trees)
        trees = [CJOJSONParser trees];
    return trees;
}
+(NSArray *) terms
{
    if(!terms)
        terms = [CJOJSONParser terms];
    return terms;
}
@end
