//
//  CJOJSONParser.m
//  Camp Joy Outdoors
//
//  Created by Brian Telintelo on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOJSONParser.h"
#import "JSONKit.h"
#import "CJOQuestion.h"


@implementation CJOJSONParser

+(NSArray *)questions
{
    NSBundle *bundle = [NSBundle bundleForClass:[self class]];
    NSString *path = [bundle pathForResource:@"questions" ofType:@"json"];
    
    NSData *jsonData = [NSData dataWithContentsOfFile: path];
    NSDictionary *jsonObject = [jsonData objectFromJSONData];
    NSArray *list = [jsonObject objectForKey:@"questions"];
    NSMutableArray *mutable = [NSMutableArray arrayWithCapacity:list.count];
    for(NSDictionary *dict in list)
    {
        CJOQuestion *question = [[CJOQuestion alloc] init];
        [question setValuesForKeysWithDictionary:dict];
        [mutable addObject:question];
    }
    return mutable;
}

@end
