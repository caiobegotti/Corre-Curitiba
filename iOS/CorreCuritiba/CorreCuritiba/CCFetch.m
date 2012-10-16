//
//  CCFetch.m
//  CorraCuritiba
//
//  Created by cits on 05/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import "CCFetch.h"

#import "CCData.h"

@implementation CCFetch

#pragma mark - Json

- (void)populateCalendar {
	responseData = [NSMutableData data];
	NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:@"http://cc.ueberalles.net/"]];
	[[NSURLConnection alloc] initWithRequest:request delegate:self];
}

- (void)connection:(NSURLConnection *)connection didReceiveResponse:(NSURLResponse *)response {
	[responseData setLength:0];
}

- (void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data {
	[responseData appendData:data];
}

- (void)connection:(NSURLConnection *)connection didFailWithError:(NSError *)error {
	NSLog(@"Calendário em manutenção, tente novamente mais tarde!");
}

- (void)connectionDidFinishLoading:(NSURLConnection *)connection {
    NSError *error = nil;
 	NSDictionary *json = [NSJSONSerialization JSONObjectWithData:responseData options:NSJSONReadingMutableLeaves error:&error];
    
	if (json == nil) {
		NSLog(@"Calendário não foi carregado corretamente!");
		NSLog(@"Erro: %@", error);
	} else {
		[[CCData sharedData] populateData:json];
	}
}

@end
