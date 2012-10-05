#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# this file is under public domain <caio1982@gmail.com>

import json
import web

# dreamhost stuff below
#from __future__ import with_statement

#import sys
#sys.path.append('/home/caio/py_libs/')

# our own stuff
import scraper

urls = (
    '/1/available', 'oneAvailable',
    '/1/json', 'oneJSON',
    '/', 'legacy'
)

app = web.application(urls, globals(), True)

class legacy():
    def GET(self):
        filename = scraper.available()[0]
        web.header('content-type', 'application/json')
        with open(filename, 'r') as data:
            res = json.dumps(json.load(data), indent=4)
            data.close()
            return res

class oneAvailable():
    def GET(self):
        return scraper.available()

class oneJSON():
    def GET(self):
        vars = web.input()
        if 'filename' in vars:
            web.header('content-type', 'application/json')
            with open(vars['filename'], 'r') as data:
                res = json.dumps(json.load(data), indent=4)
                data.close()
                return res
        else:
            return 'missing file parameter'

#web.wsgi.runwsgi = lambda func, addr=None: web.wsgi.runfcgi(func, addr)

if __name__ == "__main__":
    #web.runwsgi = web.runfcgi
    app.run()
