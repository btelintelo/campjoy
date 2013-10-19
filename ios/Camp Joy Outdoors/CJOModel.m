//
//  CJOModel.m
//  Camp Joy Outdoors
//
//  Created by Brian Telintelo on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOModel.h"
#import "CJOJSONParser.h"
#import "CJOGlossaryTerm.h"
#import "CJOQuestion.h"

@implementation CJOModel

NSArray *questions;
NSArray *trees;
NSArray *terms;
NSArray *termStrings;

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

+(NSArray *)termStrings
{
    if(!termStrings) {
        terms = [self terms];
        NSMutableArray * names = [[NSMutableArray alloc] init];
        for (CJOGlossaryTerm * glossaryTerm in terms) {
            [names addObject:glossaryTerm.name];
        }
        termStrings = names;
    }
    return termStrings;
}

+(CJOQuestion *) findQuestionById:(int) questionId {
    NSArray * questions = [self questions];
    NSString * questionIdString = [NSString stringWithFormat:@"%d", questionId];
    for(CJOQuestion * question in questions) {
        if([question.id isEqualToString:questionIdString]) {
            return question;
        }
    }
    return nil;
}

+(CJOTree*) findTreeById:(int) treeId {
    NSArray * trees = [self trees];
    NSString * treeIdString = [NSString stringWithFormat:@"%d", treeId];
    for(CJOTree * tree in trees) {
        if([tree.id isEqualToString:treeIdString]) {
            return tree;
        }
    }
    return nil;
}

+(CJOGlossaryTerm *) findTermByName:(NSString *) name {
    NSArray * terms = [self terms];
    for(CJOGlossaryTerm * term in terms) {
        if([term.name isEqualToString:name]) {
            return term;
        }
    }
    return nil;
}
@end
