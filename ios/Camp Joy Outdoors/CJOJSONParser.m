//
//  CJOJSONParser.m
//  Camp Joy Outdoors
//
//  Created by Brian Telintelo on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOJSONParser.h"
#import "CJOQuestion.h"
#import "CJOGlossaryTerm.h"
#import "CJOTree.h"
#import "CJOChoice.h"


@implementation CJOJSONParser

+(NSArray *)questions
{
    NSBundle *bundle = [NSBundle bundleForClass:[self class]];
    NSString *path = [bundle pathForResource:@"questions" ofType:@"json"];
    
    NSData *jsonData = [NSData dataWithContentsOfFile: path];
    
    NSDictionary *jsonObject = [NSJSONSerialization JSONObjectWithData:jsonData options:0 error:nil];
    NSArray *list = [jsonObject objectForKey:@"questions"];
    NSMutableArray *mutable = [NSMutableArray arrayWithCapacity:list.count];
    for(NSDictionary *dict in list)
    {
        CJOQuestion *question = [[CJOQuestion alloc] init];
        question.id = [dict objectForKey:@"id"];
        NSMutableArray *choices = [[NSMutableArray alloc] init];
        for(NSDictionary *ch in [dict objectForKey:@"choices"])
        {
            CJOChoice *choice = [[CJOChoice alloc] init];
            [choice setValuesForKeysWithDictionary:ch];
            [choices addObject:choice];
        }
        question.choices = choices;
        [mutable addObject:question];
    }
    return mutable;
}

+(NSArray *)terms
{
    NSBundle *bundle = [NSBundle bundleForClass:[self class]];
    NSString *path = [bundle pathForResource:@"glossary" ofType:@"json"];
    
    NSData *jsonData = [NSData dataWithContentsOfFile: path];
    NSDictionary *jsonObject = [NSJSONSerialization JSONObjectWithData:jsonData options:0 error:nil];    NSArray *list = [jsonObject objectForKey:@"terms"];
    NSMutableArray *mutable = [NSMutableArray arrayWithCapacity:list.count];
    for(NSDictionary *dict in list)
    {
        CJOGlossaryTerm *terms = [[CJOGlossaryTerm alloc] init];
        [terms setValuesForKeysWithDictionary:dict];
        [mutable addObject:terms];
    }
    return mutable;
    
}

+(NSArray *)trees
{
    NSBundle *bundle = [NSBundle bundleForClass:[self class]];
    NSString *path = [bundle pathForResource:@"trees" ofType:@"json"];
    
    NSData *jsonData = [NSData dataWithContentsOfFile: path];
    NSDictionary *jsonObject = [NSJSONSerialization JSONObjectWithData:jsonData options:0 error:nil];    NSArray *list = [jsonObject objectForKey:@"trees"];
    NSMutableArray *mutable = [NSMutableArray arrayWithCapacity:list.count];
    for(NSDictionary *dict in list)
    {
        CJOTree *tree = [[CJOTree alloc] init];
        [tree setValuesForKeysWithDictionary:dict];
        [mutable addObject:tree];
    }
    return mutable;
}

@end
