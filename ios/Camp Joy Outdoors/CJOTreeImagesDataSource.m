//
//  CJOTreeImagesDataSource.m
//  Camp Joy Outdoors
//
//  Created by Alex Argo on 10/19/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOTreeImagesDataSource.h"
#import "CJOTree.h"

@implementation CJOTreeImagesDataSource

- (id) initWithTree:(CJOTree *)tree {
    self = [super init];
    if(self) {
        NSString *resourcePath = [[NSBundle mainBundle] resourcePath];
        NSString *documentsPath = [NSString stringWithFormat:@"%@/trees/%@",resourcePath,tree.id];
        NSError *error;
        NSArray *directoryContents = [[NSFileManager defaultManager] contentsOfDirectoryAtPath:documentsPath error:&error];
        
        NSMutableArray *paths = [NSMutableArray array];
        for (NSString *path in directoryContents) {
            NSString *pathToAdd = [NSString stringWithFormat:@"trees/%@/%@",tree.id,path];
            [paths addObject:pathToAdd];
        }
        self.imagePaths = paths;
        
    }
    return self;
}

@end
