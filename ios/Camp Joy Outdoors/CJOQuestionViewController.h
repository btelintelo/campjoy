//
//  CJOQuestionViewController.h
//  Camp Joy Outdoors
//
//  Created by Jeremy Spitzig on 10/18/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CJOQuestion.h"
#import "CJOPathViewController.h"
#import "CJOAnswerCell.h"

@interface CJOQuestionViewController : UIViewController<UITableViewDelegate, UITableViewDataSource, CJOPathViewControllerDelegate, CJOAnswerCellDelegate>

@property (nonatomic, strong) IBOutlet UITableView * tableView;
@property (nonatomic, strong) CJOQuestion * question;
@property (nonatomic, assign) BOOL hideRestartButton;
@property (nonatomic, assign) BOOL hideHistoryButton;
@property (nonatomic, strong) NSArray * path;
@end
