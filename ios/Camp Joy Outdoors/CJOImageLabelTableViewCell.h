//
//  CJOImageLabelTableViewCell.h
//  Camp Joy Outdoors
//
//  Created by Alex Argo on 10/19/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CJOImageLabelTableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UIImageView *imageView;
@property (weak, nonatomic) IBOutlet UILabel *nameLabel;
@end
