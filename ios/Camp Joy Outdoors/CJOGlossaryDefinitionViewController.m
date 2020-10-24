//
//  CJOGlossaryDefinitionViewController.m
//  Camp Joy Outdoors
//
//  Created by Samuel Corder on 10/19/13.
//  Copyright (c) 2013 Camp Joy. All rights reserved.
//

#import "CJOGlossaryDefinitionViewController.h"
#import "CJOGlossaryTerm.h"

@interface CJOGlossaryDefinitionViewController ()

@property (weak, nonatomic) IBOutlet UIImageView *definitionImageView;
@property (weak, nonatomic) IBOutlet UILabel *descriptionLabel;

@end

@implementation CJOGlossaryDefinitionViewController

-(void)viewDidLoad
{
    if (_word) {
        [self updateDisplay];
    }
}

-(void)setWord:(NSString *)word
{
    _word = word;
    [self updateDisplay];
}

-(void)updateDisplay
{
    CJOGlossaryTerm *term = [CJOModel findTermByName:_word];
    self.title = term.name;
    self.descriptionLabel.text = term.description2;
    NSString *fmt = @"glossary/%@";
    NSString *imageName = [NSString stringWithFormat:fmt, term.image];
    self.definitionImageView.image = [UIImage imageNamed:imageName];
}

@end
