//
//  CJOTreeInfoViewController.m
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOTreeInfoViewController.h"
#import "CJOModel.h"
#import "CJOTreeImagesDataSource.h"
#import "CJOBigImageCell.h"

@interface CJOTreeInfoViewController ()
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *descriptionHeightConstraint;
@property (weak, nonatomic) IBOutlet UILabel *scientificNameLabel;
@property (weak, nonatomic) IBOutlet UITextView *descriptionTextView;
@property (weak, nonatomic) IBOutlet iCarousel *carousel;
@property (strong, nonatomic) CJOTreeImagesDataSource* imagesDataSource;
@end

@implementation CJOTreeInfoViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)resizeHeaderViewsForDescriptionText
{

    CGFloat descriptionHeight = [self heightForTextView:self.descriptionTextView];
    self.descriptionHeightConstraint.constant = descriptionHeight;
    self.tableView.tableHeaderView.bounds = CGRectMake(0, 0, self.tableView.bounds.size.width, descriptionHeight + self.carousel.bounds.size.height + 10);
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.imagesDataSource = [[CJOTreeImagesDataSource alloc] initWithTree:self.tree];
    self.carousel.dataSource = self.imagesDataSource;
    self.navigationItem.title = self.tree.name;
    self.scientificNameLabel.text = [NSString stringWithFormat:@"%@ - %@",self.tree.sciname,self.tree.family];
    self.descriptionTextView.text = self.tree.description;

    [self resizeHeaderViewsForDescriptionText];
    [self configureCarousel];
}

- (IBAction)done:(id)sender {
    [self.navigationController popToRootViewControllerAnimated:YES];
}

- (void) configureCarousel {
    self.carousel.decelerationRate = 0.5;
}

#pragma mark - UITableViewDataSource;
- (NSInteger) numberOfSectionsInTableView:(UITableView *)tableView {
    return 3;
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section {
    if(section == 0) {
        return @"Information";
    } else if (section == 1) {
        return @"Tree Silhouette";
    } else {
        return @"Where It Grows";
    }
    
}

- (NSInteger) tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    if(section == 0) {
        return self.tree.tableData.count;
    } else {
        return 1;
    }
}

- (CGFloat) tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    if(indexPath.section == 0) {
        return 48;
    } else {
        return 240;
    }
}

- (void)configureDataCell:(UITableViewCell *)cell forIndexPath:(NSIndexPath *)indexPath {
    NSDictionary *data = self.tree.tableData[indexPath.row];
    NSString *name = data.allKeys[0];
    NSString *value = data[name];
    cell.textLabel.text = name;
    cell.detailTextLabel.text = value;
    cell.detailTextLabel.numberOfLines = 0;
    cell.detailTextLabel.font = [UIFont systemFontOfSize:14];
    cell.textLabel.font = [UIFont boldSystemFontOfSize:14.0];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    if(indexPath.section == 0){
        UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"TreeDataCell"];
        [self configureDataCell:cell forIndexPath:indexPath];
        return cell;
    } else if (indexPath.section == 1) {
        CJOBigImageCell *cell = [tableView dequeueReusableCellWithIdentifier:@"BigImageCell"];
        cell.bigImageView.image = [UIImage imageNamed:[NSString stringWithFormat:@"thumb/%@_thumb.jpg",self.tree.id]];
        return cell;
    } else if (indexPath.section == 2) {
        CJOBigImageCell *cell = [tableView dequeueReusableCellWithIdentifier:@"BigImageCell"];
 cell.bigImageView.image = [UIImage imageNamed:[NSString stringWithFormat:@"maps/%@_map.jpg",self.tree.id]];
        return cell;
    } else {
        return [tableView dequeueReusableCellWithIdentifier:@"TreeDataCell"];
    }
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


#pragma mark - iCarouselDelegate {
- (void)carousel:(iCarousel *)carousel didSelectItemAtIndex:(NSInteger)index{
    MWPhotoBrowser *browser = [[MWPhotoBrowser alloc] initWithDelegate:self.imagesDataSource];
    [browser setCurrentPhotoIndex:index];
    UINavigationController *navigationController = [[UINavigationController alloc] initWithRootViewController:browser];
    UIViewController *rootViewController = [[UIApplication sharedApplication] keyWindow].rootViewController;
    [rootViewController presentViewController:navigationController animated:YES completion:nil];
}

#pragma mark - Utilities
- (CGFloat)heightForTextView:(UITextView *)textView {
    CGRect frame = textView.bounds;
    
    // Take account of the padding added around the text.
    
    UIEdgeInsets textContainerInsets = textView.textContainerInset;
    UIEdgeInsets contentInsets = textView.contentInset;
    
    CGFloat leftRightPadding = textContainerInsets.left + textContainerInsets.right + textView.textContainer.lineFragmentPadding * 2 + contentInsets.left + contentInsets.right;
    CGFloat topBottomPadding = textContainerInsets.top + textContainerInsets.bottom + contentInsets.top + contentInsets.bottom;
    
    frame.size.width -= leftRightPadding;
    frame.size.height -= topBottomPadding;
    
    NSString *textToMeasure = textView.text;
    if ([textToMeasure hasSuffix:@"\n"])
    {
        textToMeasure = [NSString stringWithFormat:@"%@-", textView.text];
    }
    
    // NSString class method: boundingRectWithSize:options:attributes:context is
    // available only on ios7.0 sdk.
    
    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    [paragraphStyle setLineBreakMode:NSLineBreakByWordWrapping];
    
    NSDictionary *attributes = @{ NSFontAttributeName: textView.font, NSParagraphStyleAttributeName : paragraphStyle };
    
    CGRect size = [textToMeasure boundingRectWithSize:CGSizeMake(CGRectGetWidth(frame), MAXFLOAT)
                                              options:NSStringDrawingUsesLineFragmentOrigin
                                           attributes:attributes
                                              context:nil];
    
    CGFloat measuredHeight = ceilf(CGRectGetHeight(size) + topBottomPadding);
    return measuredHeight+8.0f;
    
}

@end
