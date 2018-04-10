import sys
import jieba
from os import path
from collections import Counter
from importlib import reload
import numpy as np
import matplotlib.mlab as mlab
import matplotlib
import matplotlib.pyplot as plt
import matplotlib as mpl

d=path.dirname(__file__)
text_path = "D:\\test.txt"

stopwords_path = 'D:\\stop.txt' # 停用词词表
text = open(path.join(d,text_path)).read()

def jiebaclearText(text):
    mywordlist = []
    seg_list = jieba.cut(text, cut_all=False) #默认模式
    liststr="/ ".join(seg_list)
    f_stop = open(stopwords_path)
    try:
        f_stop_text = f_stop.read( )
    finally:
        f_stop.close( )
    f_stop_seg_list=f_stop_text.split('\n')
    for myword in liststr.split('/'):
        if not(myword.strip() in f_stop_seg_list) and len(myword.strip())>1:
            mywordlist.append(myword)
    return mywordlist


def get_words(txt):
    seg_list = txt
    c = Counter()
    for x in seg_list:
        if len(x)>1 and x != '\r\n':
            c[x] += 1
    print('常用词频度统计结果')
    for (k,v) in c.most_common(len(seg_list)):
        print('%s   %d' %(k,v))

    X = []
    Y = []
    for (k,v) in c.most_common(len(seg_list)):
        X.append(k)
        Y.append(v)

    fig = plt.figure()
    plt.bar(X, Y, 0.5, color="green")
    plt.xlabel("词汇",fontproperties=zhfont1)
    plt.ylabel("次数",fontproperties=zhfont1)

    plt.show()

zhfont1 = matplotlib.font_manager.FontProperties(fname='C:\Windows\Fonts\simkai.ttf')
result = jiebaclearText(text)
print(result)
get_words(result)





