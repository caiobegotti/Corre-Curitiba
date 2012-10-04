#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# this file is under public domain <caio1982@gmail.com>

import json
import web

# our own stuff
import scraper

urls = (
    '/1/available', 'oneAvailable',
    '/1/json', 'oneJSON'
)

app = web.application(urls, globals(), True)

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
