import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './bii.reducer';
import { IBII } from 'app/shared/model/bii.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBIIProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class BII extends React.Component<IBIIProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { bIIList, match } = this.props;
    return (
      <div>
        <h2 id="bii-heading">
          BIIS
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new BII
          </Link>
        </h2>
        <div className="table-responsive">
          {bIIList && bIIList.length > 0 ? (
            <Table responsive aria-describedby="bii-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Type</th>
                  <th>Bii Id</th>
                  <th>Detection Timestamp</th>
                  <th>Source Id</th>
                  <th>Detection System Name</th>
                  <th>Detected Value</th>
                  <th>Detection Context</th>
                  <th>Etc</th>
                  <th>Etcetc</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {bIIList.map((bII, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${bII.id}`} color="link" size="sm">
                        {bII.id}
                      </Button>
                    </td>
                    <td>{bII.name}</td>
                    <td>{bII.type}</td>
                    <td>{bII.biiId}</td>
                    <td>{bII.detectionTimestamp}</td>
                    <td>{bII.sourceId}</td>
                    <td>{bII.detectionSystemName}</td>
                    <td>{bII.detectedValue}</td>
                    <td>{bII.detectionContext}</td>
                    <td>{bII.etc}</td>
                    <td>{bII.etcetc}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${bII.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${bII.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${bII.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No BIIS found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ bII }: IRootState) => ({
  bIIList: bII.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BII);
