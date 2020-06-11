import { IPromise } from 'angular';
import { API } from '@spinnaker/core';

export class DroneService {

  public static listMasters() : IPromise<string[]>{
      return API
        .one('drone-ci')
        .one('masters')
        .get();
  }

  public static listNamespacesForMaster(master: String) : IPromise<string[]>{
    return API
        .one('drone-ci')
        .one('masters')
        .one(master)
        .one('namespaces')
        .get();
  }

  public static listReposForNamespace(master: String, namespace: String) :IPromise<string[]>{
    return API
        .one('drone-ci')
        .one('masters')
        .one(master)
        .one('namespaces')
        .one(namespace)
        .one('repos')
        .get();
  }

}
