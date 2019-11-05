import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BII from './bii';
import BIIDetail from './bii-detail';
import BIIUpdate from './bii-update';
import BIIDeleteDialog from './bii-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BIIUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BIIUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BIIDetail} />
      <ErrorBoundaryRoute path={match.url} component={BII} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={BIIDeleteDialog} />
  </>
);

export default Routes;
