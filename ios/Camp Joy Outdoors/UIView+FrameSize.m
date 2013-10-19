//
//  UIView+FrameSize.m
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/19/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "UIView+FrameSize.h"

@implementation UIView (FrameSize)
-(void) setHeight:(int) height {
    CGPoint origin = self.frame.origin;
    CGSize size = self.frame.size;
    self.frame = CGRectMake(origin.x, origin.y, size.width, height);
}
@end
