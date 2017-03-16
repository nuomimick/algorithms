# -*- coding: utf-8 -*-
"""
Created on Thu Mar 16 13:20:48 2017

@author: nuomi
"""
from random import sample,random,randint
from math import floor

class SGA:
    def __init__(self,m,t,pc,pm):
        '''
        m:种群规模
        t:进化代数
        pc:交叉概率
        pm:变异概率
        '''
        self.m = m
        self.t = t
        self.pc = pc
        self.pm = pm
              
        self.strLength = 6
        self.popul = []
        
    def adaptive_fun(self,x):
        return x**2
        
    def init_population(self):
        #初始化种群
        popul = sample(range(64),self.m)
        for x in popul:
            length = self.strLength - len(bin(x)) + 2
            if length > 0:
                self.popul.append('0'*length+bin(x)[2:])
            else:
                self.popul.append(bin(x)[2:])
        print(self.popul)
        
    def probability_select(self):
        #累计概率
        adapt_p =[self.adaptive_fun(int(x,2)) for x in self.popul]
        probs = [p / sum(adapt_p) for p in adapt_p]
        accum_p = []
        temp = 0
        for p in probs:
            temp += p
            accum_p.append(temp)
        return accum_p

    def selection(self):
        #适应性选择
        accum_p = self.probability_select()
        popul = []
        for _ in range(self.m):
            r = random()
            for idx,p in enumerate(accum_p):
                if r <= p:
                    popul.append(self.popul[idx])
                    break
        self.popul = popul
        print(self.popul)
        
    def hibridization(self):
        #杂交
        popul = self.popul.copy()
        mpopul = []
        while popul:
            hlist = sample(popul,2)
            popul.remove(hlist[0])
            popul.remove(hlist[1])
            if random() < self.pc:
                pos = randint(floor(self.strLength / 2),self.strLength-1)
                mpopul.append(hlist[0][:pos] + hlist[1][pos:])
                mpopul.append(hlist[1][:pos] + hlist[0][pos:])
            else:
                mpopul.extend(hlist)
        self.popul = mpopul
        print(self.popul)
        
    def mutation(self):
        #变异
        for idx,x in enumerate(self.popul):
            if random() < self.pm:
                pos = randint(0,self.strLength-1)
                l = list(x)
                if l[pos] == '0':
                    l[pos] = '1'
                else:
                    l[pos] = '0'
                self.popul[idx] = ''.join(l)
        print(self.popul)
            
    def fit(self):
        self.init_population()
        for _ in range(self.t):
            self.selection()
            self.hibridization()
            self.mutation()
            
if __name__ == '__main__':
    sga = SGA(8,10,0.8,0.01)
    sga.fit()
    
    