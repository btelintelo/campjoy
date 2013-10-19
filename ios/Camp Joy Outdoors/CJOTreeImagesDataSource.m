//
//  CJOTreeImagesDataSource.m
//  Camp Joy Outdoors
//
//  Created by Alex Argo on 10/19/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOTreeImagesDataSource.h"

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

- (NSUInteger)numberOfItemsInCarousel:(iCarousel *)carousel {
    return  [self.imagePaths count];
}

- (UIView *)carousel:(iCarousel *)carousel viewForItemAtIndex:(NSUInteger)index reusingView:(UIView *)view {
    UIView *viewContainer = [[UIView alloc] initWithFrame:CGRectMake(0,0,288,210)];
    UIImage *image = [UIImage imageNamed:self.imagePaths[index]];
    UIImageView *imageView = [[UIImageView alloc] initWithImage:image];
    imageView.frame = CGRectMake(4,0,280,210);
    imageView.contentMode = UIViewContentModeScaleToFill;
    [viewContainer addSubview:imageView];
    return viewContainer;
}


- (void)carousel:(iCarousel *)carousel didSelectItemAtIndex:(NSInteger)index {
    NSLog(@"Selected index %d",index);
}

- (NSUInteger)numberOfPhotosInPhotoBrowser:(MWPhotoBrowser *)photoBrowser {
    return [self.imagePaths count];
}
- (id<MWPhoto>)photoBrowser:(MWPhotoBrowser *)photoBrowser photoAtIndex:(NSUInteger)index {
    NSString *imagePath = self.imagePaths[index];
    MWPhoto *photo = [MWPhoto photoWithImage:[UIImage imageNamed:imagePath]];
    return photo;
}
@end
