import React from 'react';

import Select, { Option } from 'react-select';
import { StageConfigField, IStageConfigProps } from '@spinnaker/core';
import { DroneService } from '../services/droneci.service';

export interface IDroneCIStageConfigState {
  masters: string[];
  namespaces: string[];
  repos: string[];
}

export interface IDroneCIStage {
  master?: string;
  namespace?: string;
  repo?: string;
}

export class DroneCIStageConfig extends React.Component<IStageConfigProps, IDroneCIStageConfigState> {
  constructor(props: IStageConfigProps) {
    super(props);
    const { master, namespace, repo } = props.stage as IDroneCIStage;
    this.state = {
      masters: master ? [master] : [],
      namespaces: namespace ? [namespace] : [],
      repos: repo ? [repo] : [],
    };
  }

  public componentDidMount(): void {
    DroneService.listMasters().then((masters: string[]) => {
      this.setState({ masters})
    });

    this.fetchAvailableNamespaces();
    this.fetchAvailableRepos();
  }

  public render() {
    const { masters, namespaces, repos } = this.state;
    const { master, namespace, repo } = this.getStage();

    return (
      <>
        <StageConfigField label="Build Master">
          <Select
            value={master}
            placeholder="Select a build master..."
            onChange={this.onMasterChanged}
            options={masters.map((name: string) => ({ label: name, value: name }))}
            clearable={false}
          />
        </StageConfigField>
        <StageConfigField label="Build Namespace">
          {!master && <p className="form-control-static">(Select a build master)</p>}
          {master && (
            <Select
              value={namespace}
              placeholder="Select a namespace..."
              onChange={this.onNamespaceChanged}
              options={namespaces.map((name: string) => ({ label: name, value: name }))}
              clearable={false}
            />
          )}
        </StageConfigField>
        <StageConfigField label="Repository">
          {!namespace && <p className="form-control-static">(Select a build master and namespace)</p>}
          {namespace && (
            <Select
              value={repo}
              placeholder="Select a repository..."
              onChange={this.onRepoChanged}
              options={repos.map((name: string) => ({ label: name, value: name }))}
              clearable={false}
            />
          )}
        </StageConfigField>
      </>
    );
  }

  private onMasterChanged = (option: Option<string>) => {
    const stage = this.getStage();
    if (stage.master === option.value) {
      return;
    }
    this.props.updateStageField({
      master: option.value,
      namespace: null,
      repo: null,
    });
    this.fetchAvailableNamespaces();
  };

  private onNamespaceChanged = (option: Option<string>) => {
    const stage = this.getStage();
    if (stage.namespace === option.value) {
      return;
    }
    this.props.updateStageField({
      namespace: option.value,
      repo: null,
    });
    this.fetchAvailableRepos();
  };

  private onRepoChanged = (option: Option<string>) => {
    const stage = this.getStage();
    if (stage.repo === option.value) {
      return;
    }
    this.props.updateStageField({
      repo: option.value
    });
    this.props.stageFieldUpdated();
  };

  private fetchAvailableNamespaces = () => {
    const { master } = this.getStage();
    if (master) {
      DroneService.listNamespacesForMaster(master).then(namespaces => this.setState({ namespaces: namespaces }));
    }
  };

  private fetchAvailableRepos = () => {
    const { master, namespace } = this.getStage();
    if (master && namespace) {
      DroneService.listReposForNamespace(master, namespace).then(repos => this.setState({ repos: repos }));
    }
  };

  private getStage() {
    return this.props.stage as IDroneCIStage;
  }
}
