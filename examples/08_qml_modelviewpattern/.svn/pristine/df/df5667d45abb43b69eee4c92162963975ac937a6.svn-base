#ifndef MODEL_H
#define MODEL_H

#include <vector>
#include <QAbstractListModel>
#include <QBrush>

class Model: public QAbstractListModel
{
   Q_OBJECT

public:

   // ctor
   Model(QObject *parent=NULL): QAbstractListModel(parent) {}

   // virtual functions of the abstract model to be implemented:

   int rowCount(const QModelIndex &parent = QModelIndex()) const {return(data_.size());}
   int columnCount(const QModelIndex &parent = QModelIndex()) const {return(1);}

   QVariant data(const QModelIndex &index, int role = Qt::DisplayRole) const
   {
      if (role == Qt::DisplayRole)
         return(data_[index.row()]);
      else if (role == Qt::BackgroundRole)
         return(QBrush(Qt::green));

      return(QVariant());
   }

   bool setData(const QModelIndex &index, const QVariant &value, int role)
   {
      if (role == Qt::EditRole)
      {
         data_[index.row()] = value.toString();
         emit dataChanged(index, index);
      }

      return(true);
   }

   Qt::ItemFlags flags(const QModelIndex &index) const
   {
      return(Qt::ItemIsEnabled | Qt::ItemIsEditable);
   }

   // accessors of the data model:

   void add(QString s)
   {
      data_.push_back(s);

      QModelIndex index = createIndex(data_.size()-1, 0);
      emit dataChanged(index, index);
   }

protected:

   std::vector<QString> data_;
};

#endif
