//
//  CJOPathCell.m
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/19/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOPathCell.h"

@implementation CJOPathCell

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
    }
    return self;
}

-(void)layoutSubviews {
    self.stepLabel.text = self.choice.text;
}

@end
