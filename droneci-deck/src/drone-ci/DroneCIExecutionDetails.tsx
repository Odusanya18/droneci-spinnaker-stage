import React from 'react';

import {
  ExecutionDetailsSection,
  IExecutionDetailsSectionProps,
  StageFailureMessage,
  StageExecutionLogs,
} from '@spinnaker/core';

export function DroneCIExecutionDetails({
  stage: { context = {} },
  stage,
  name,
  current,
}: IExecutionDetailsSectionProps) {
  return (
    <ExecutionDetailsSection name={name} current={current}>
      <dl className="dl-narrow dl-horizontal">
        <dt>Build Master</dt>
        <dd>{context.master}</dd>
        <dt>Build Namespace</dt>
        <dd>{context.namespace}</dd>
        <dt>Repository</dt>
        <dd>{context.repo}</dd>
        <dt>Build</dt>
        <dd>{context.buildNumber}</dd>
      </dl>
      <StageFailureMessage stage={stage} message={stage.failureMessage} />
      <StageExecutionLogs stage={stage} />
    </ExecutionDetailsSection>
  );
}

// TODO: refactor this to not use namespace
// eslint-disable-next-line
export namespace DroneCIExecutionDetails {
  export const title = 'droneciConfig';
}
