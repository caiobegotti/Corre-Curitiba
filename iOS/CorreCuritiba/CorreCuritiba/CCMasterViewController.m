//
//  CCMasterViewController.m
//  CorreCuritiba
//
//  Created by cits on 10/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import "CCMasterViewController.h"

#import "CCDetailViewController.h"

#import "CCFetch.h"

#import "CCData.h"

#import "CCEvent.h"

@interface CCMasterViewController () {
    NSMutableArray *_objects;
}
@end

@implementation CCMasterViewController

- (void)awakeFromNib
{
    if ([[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPad) {
        self.clearsSelectionOnViewWillAppear = NO;
        self.contentSizeForViewInPopover = CGSizeMake(320.0, 600.0);
    }
    [super awakeFromNib];
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	self.detailViewController = (CCDetailViewController *)[[self.splitViewController.viewControllers lastObject] topViewController];
    
    // Visual cue that there are stuff still being loaded
	activity = [[UIActivityIndicatorView alloc] initWithFrame:CGRectMake(0.0f, 0.0f, 32.0f, 32.0f)];
    [activity setCenter:CGPointMake(160.0f, 208.0f)];
    [activity setActivityIndicatorViewStyle:UIActivityIndicatorViewStyleGray];
    [self.tableView addSubview:activity];
	[self.view setUserInteractionEnabled: NO];
	[activity startAnimating];
	
	// Dispatch the JSON downloader and parser
	// to populate NSMutableArray *events from CCEvents
	CCFetch *fetch = [CCFetch new];
	[fetch populateCalendar];
	
	// Registers a reloader for the tableView data
	[[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(forceReload) name:@"reloadRequest" object:nil];
}

- (void)viewDidUnload
{
	[[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)insertNewObject:(id)sender
{
    if (!_objects) {
        _objects = [[NSMutableArray alloc] init];
    }
    [_objects insertObject:[NSDate date] atIndex:0];
    NSIndexPath *indexPath = [NSIndexPath indexPathForRow:0 inSection:0];
    [self.tableView insertRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationAutomatic];
}

#pragma mark - Table View

- (void)forceReload
{
	[self.tableView reloadData];
}

/*
// UNUSED
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    NSArray *set = [[CCDataSections alloc] getDataSections];
    return [set count];
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section
{
    NSArray *set = [[CCDataSections alloc] getDataSections];
    return [set objectAtIndex:section];
}

// TODO
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{    
    NSArray *data = [[CCData sharedData] getData];
    CCEvent *event = [[CCEvent alloc] initWithParam:section];

    NSMutableArray *sections = [[NSMutableArray alloc] init];
    
    for (NSDictionary *entry in data) {
        if ([[entry objectForKey:@"Data:"] isEqualToString:event.date]) {
            [sections addObject:[entry objectForKey:@"Data:"]];
        }
    }
    
    return [sections count];
}
*/

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [[[CCData sharedData] getData] count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"Cell" forIndexPath:indexPath];
    
	// Visual cue that we're done loading web data and ready to make them visible in cells
	[self.view setUserInteractionEnabled: YES];
	[activity stopAnimating];
	
    CCEvent *event = [[CCEvent alloc] initWithParam:[indexPath row]];
        
    NSString *subtitle;
    subtitle = [NSString stringWithFormat:@"%1$@ — %2$@", event.date, event.distance];
    
	cell.detailTextLabel.text = subtitle;
    cell.textLabel.text = event.name;
    
	return cell;
}

- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath
{
    return NO;
}

- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        [_objects removeObjectAtIndex:indexPath.row];
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
    } else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view.
    }
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    if ([[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPad) {
		NSMutableArray *items = [[CCData sharedData] getData];
		NSMutableDictionary *selected = [items objectAtIndex:indexPath.row];
        self.detailViewController.detailItem = selected;
    }
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([[segue identifier] isEqualToString:@"showDetail"]) {
        NSIndexPath *indexPath = [self.tableView indexPathForSelectedRow];
		NSMutableArray *items = [[CCData sharedData] getData];
		NSMutableDictionary *selected = [items objectAtIndex:indexPath.row];
        [[segue destinationViewController] setDetailItem:selected];
    }
}

@end
