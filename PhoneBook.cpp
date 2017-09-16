#include <iostream>
#include <string>
#include <sstream>
#include <math.h>

using namespace std;
struct node{
    long phnNo;
    string name;
    node *next;
};

class LinkedList{

private:
    node *p;
    node *head,*tail;

public:
    LinkedList(){
        p=NULL;
        head=NULL;
        tail=NULL;
    }

    void pushFront(long phnNo,string name)
    {
        node *temp;
        temp=new node;
        temp->phnNo=phnNo;
        temp->name=name;
        temp->next=head;
        head=temp;
        if(tail==NULL)
            tail=head;
    }
    void popFront()
    {
        if(head==NULL)
            return;
        head=head->next;
        if(head==NULL)
            tail=NULL;
    }
    void pushBack(long phnNo,string name)
    {
        node *temp=new node;
        temp->phnNo=phnNo;
        temp->name=name;
        temp->next=NULL;
        if(tail==NULL)
            head=tail=temp;
        else
            tail->next=temp;
            tail=temp;
    }
    void popBack()
    {
        if(head==NULL)
            return;
        if(head==tail)
            head=tail=NULL;
        else
        {
            p=head;
            while(p->next->next!=NULL)
                p=p->next;
            p->next=NULL;
            tail=p;
        }
    }
    node* getHead()
    {
        return head;
    }

    void delNode(long phnNo)
    {
        if(head==NULL)
            return;
        if(head==tail)
            head=tail=NULL;
        else
        {
            p=head;
            while(p->next!=NULL)
            {
                if(p->next->phnNo==phnNo)
                    break;
                p=p->next;
            }
            if(tail==p->next)
                tail=p;
            node *temp=p->next;
            temp->next=p->next->next;
            p->next->next=NULL;
            delete p->next;
            p->next=temp->next;

        }
    }
    void pushUpdate(node *temp)
    {
        p=temp;
        p->name=temp->name;
    }


};

int h(long phn)
{
    return ((34*phn+2)%10000003)%1000;
}
void add(LinkedList *link,long phn,string name)
{
        node *p;
        p=link->getHead();

            while(p!=NULL)
            {
                if(p->phnNo==phn)
                    {

                        p->name=name;
                        link->pushUpdate(p);
                        return;
                    }
                p=p->next;
            }
            link->pushBack(phn,name);

}

string findName(LinkedList *link,long phn)
{
    node *p;
    p=link->getHead();
        while(p!=NULL)
        {
            if(p->phnNo==phn)
                return (p->name);
            p=p->next;
        }
        return "not found";
}

void del(LinkedList *link,long phn)
{
    if((findName(link,phn))=="not found")
        return;
    link->delNode(phn);
}

int main()
{
    long n;
    cin>>n;
    string s;
    getline(cin,s);
    LinkedList *link[1000];
    for(int i=0;i<1000;i++)
        link[i]=new LinkedList();
    for(int t=0;t<n;t++)
    {
    getline(cin,s);


    int findex=s.find_first_of(" ");
    int lindex=s.find_last_of(" ");
    if(findex==lindex)
    {
        string phnNo=s.substr(findex+1,s.length());
        stringstream obj(phnNo);

        int phn=0;
        obj >> phn;
    int hashVal=h(phn);
        if(findex==3)
        {
            del(link[hashVal],phn);
        }
        else
        {
            string name=findName(link[hashVal],phn);
            cout<<name<<endl;
        }
    }
    else
    {

        string phnNo=s.substr(findex+1,lindex-findex-1);
        string name=s.substr(lindex+1,s.length());
        stringstream obj(phnNo);

        int phn=0;
        obj >> phn;
        int hashVal=h(phn);
        add(link[hashVal],phn,name);
    }

    }

    for(int i=0;i<1000;i++)
        link[i]=NULL;

    return 0;
}
